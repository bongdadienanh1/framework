/*
 * Copyright (c) 2018. CK. All rights reserved.
 */

package expression;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: CK
 * @date: 2018/6/15
 */
public class ExpressionParserTest {

    @Test
    public void one() {
        //创建SpEL表达式的解析器
        ExpressionParser parser = new SpelExpressionParser();
        // -- AND 与运算 --
        //false
        boolean falseValue4 = parser.parseExpression("true and false").getValue(Boolean.class);
        Assert.assertFalse(falseValue4);
        // -- OR 或运算--
        //true
        boolean trueValue5 = parser.parseExpression("true or false").getValue(Boolean.class);
        Assert.assertTrue(trueValue5);
        //false
        boolean falseValue5 = parser.parseExpression("!true").getValue(Boolean.class);
        Assert.assertFalse(falseValue5);
    }

    @Test
    public void two() {
        //创建SpEL表达式的解析器
        ExpressionParser parser = new SpelExpressionParser();
        User user = new User(9527, "周星驰");
        //解析表达式需要的上下文，解析时有一个默认的上下文
        EvaluationContext ctx = new StandardEvaluationContext();
        //在上下文中设置变量，变量名为user，内容为user对象
        ctx.setVariable("user", user);
        //从用户对象中获得id并+1900，获得解析后的值在ctx上下文中
        //11427
        int id = parser.parseExpression("#user.getId() + 1900").getValue(ctx, Integer.class);
        Assert.assertEquals(id, 11427);
        //true
        boolean flag = parser.parseExpression("#user.getId() == 9527").getValue(ctx, Boolean.class);
        Assert.assertTrue(flag);
    }
}
