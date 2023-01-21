package ru.spb.svga.bot.expressions.spelexpressions.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import ru.spb.svga.bot.expressions.spelexpressions.configuration.ExpressionsConfiguration;

import java.util.Objects;
import java.util.function.Predicate;

import static java.lang.Boolean.*;
import static ru.spb.svga.bot.expressions.spelexpressions.configuration.ExpressionsConfiguration.Beans.*;

@RequiredArgsConstructor
@Slf4j
public class TransitionExpression {

    private final Expression expression;
    private final EvaluationContext evaluationContext;

    public String asString() {
        return expression.getExpressionString();
    }

    public Predicate<InteractionContext> asPredicate() {
        return this::evaluate;
    }

    private boolean evaluate(InteractionContext context) {
        try {
            var result = expression.getValue(evaluationContext, context, Boolean.class);
            return Objects.nonNull(result) && result;
        } catch (EvaluationException e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
