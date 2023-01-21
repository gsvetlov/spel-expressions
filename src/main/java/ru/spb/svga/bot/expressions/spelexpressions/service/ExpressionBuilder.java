package ru.spb.svga.bot.expressions.spelexpressions.service;

import com.google.common.base.Suppliers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;
import ru.spb.svga.bot.expressions.spelexpressions.configuration.ExpressionsConfiguration;

import java.util.Optional;

@Component
@Slf4j
public class ExpressionBuilder {

    private final ExpressionParser parser;

    public ExpressionBuilder(
            @Qualifier(ExpressionsConfiguration.Beans.CONFIGURED_PARSER)
            ExpressionParser parser) {
        this.parser = parser;
    }

    public Optional<Expression> build(String expressionString) {
        try {
            return Optional.of(parser.parseExpression(expressionString));
        } catch (ParseException e) {
            log.error("parsing failed {}", e.toDetailedString());
            return Optional.empty();
        }
    }

}
