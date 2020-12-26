package com.demo.graphql.springboot;

import graphql.servlet.GraphQLProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jj
 * @since 2020/10/4 16:05
 */
@Component
public class Problem {
    @Autowired
    private GraphQLProvider graphQLProvider;
}
