package ru.spb.svga.bot.expressions.spelexpressions.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;

@Configuration
public class ExpressionsConfiguration {


    @Bean(Beans.CONFIGURED_PARSER)
    public ExpressionParser getCOnfiguredExpressionParser() {
        SpelParserConfiguration config = new SpelParserConfiguration(SpelCompilerMode.MIXED,
                                                                     this.getClass().getClassLoader(),
                                                                     false,
                                                                     false,
                                                                     0);
        return new SpelExpressionParser(config);
    }

    @Bean(Beans.CONFIGURED_CONTEXT)
    public EvaluationContext getConfiguredEvaluationContext() {
        return new StandardEvaluationContext();
    }

    @Bean(Beans.SIMPLE_CONTEXT)
    public EvaluationContext getSimpleEvaluationContext() {
        return SimpleEvaluationContext.forReadWriteDataBinding().build();
    }

    @Bean(Beans.READONLY_CONTEXT)
    public EvaluationContext getReadonlyEvaluationContext() {
        return SimpleEvaluationContext.forReadOnlyDataBinding().build();
    }

    public static final class Beans {
        public static final String CONFIGURED_PARSER = "configuredEvaluationParser";
        public static final String CONFIGURED_CONTEXT = "configuredEvaluationContext";
        public static final String SIMPLE_CONTEXT = "simpleEvaluationContext";
        public static final String READONLY_CONTEXT = "readonlyEvaluationContext";

        private Beans() {
            throw new UnsupportedOperationException();
        }
    }
}
