package ru.spb.svga.bot.expressions.spelexpressions.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import ru.spb.svga.bot.expressions.spelexpressions.configuration.ExpressionsConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransitionExpressionTest {

    @Autowired
    @Qualifier(ExpressionsConfiguration.Beans.READONLY_CONTEXT)
    private EvaluationContext context;

    @Autowired
    private ExpressionBuilder builder;

    @Test
    void testExpression_true() {
        String expr = "true";
        var expression = builder.build(expr).orElseThrow();
        var transition = new TransitionExpression(expression, context);

        boolean test = transition.asPredicate().test(new InteractionContext() { });
        assertEquals(expr, transition.asString());
        assertTrue(test);
    }

    @Test
    void testExpression_contents() {
        String expr = "command eq '/123' and rating > 100 and text matches '.*[Hh][Ii].*'";
        var expression = builder.build(expr).orElseThrow();
        var transition = new TransitionExpression(expression, context);

        boolean test = transition.asPredicate().test(new InteractionContext() {
            public Integer getRating() {
                return 150;
            }
            public String getCommand() {
                return "/123";
            }

            public String getText() {
                return "Hi, there";
            }
        });
        assertEquals(expr, transition.asString());
        assertTrue(test);
    }
}