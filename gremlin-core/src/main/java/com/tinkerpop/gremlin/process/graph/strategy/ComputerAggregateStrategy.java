package com.tinkerpop.gremlin.process.graph.strategy;

import com.tinkerpop.gremlin.process.Step;
import com.tinkerpop.gremlin.process.Traversal;
import com.tinkerpop.gremlin.process.TraversalStrategy;
import com.tinkerpop.gremlin.process.computer.traversal.step.sideEffect.AggregateComputerStep;
import com.tinkerpop.gremlin.process.graph.step.sideEffect.AggregateStep;
import com.tinkerpop.gremlin.process.util.TraversalHelper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class ComputerAggregateStrategy implements TraversalStrategy.FinalTraversalStrategy {

    public void apply(final Traversal traversal) {
        ((List<Step>) traversal.getSteps()).stream()
                .filter(step -> step instanceof AggregateStep)
                .collect(Collectors.<Step>toList())
                .forEach(step -> TraversalHelper.replaceStep(step, new AggregateComputerStep(traversal, (AggregateStep) step), traversal));
    }
}
