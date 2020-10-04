package com.demo.graphql.simple;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Resources;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.junit.Test;

import java.io.IOException;

import static com.demo.graphql.simple.TestDataFetchers.*;
import static graphql.ExecutionInput.newExecutionInput;
import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

/**
 * @author jj
 * @since 2020/10/3 16:26
 */
public class TestGraphQL {

    @Test
    public void fun() throws IOException {
        /* 构建GraphQL */
        // schema定义语句
        String sdl = Resources.toString(Resources.getResource("simple/schema.graphqls"), Charsets.UTF_8);
        SchemaParser schemaParser = new SchemaParser();

        TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(sdl);

        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query").dataFetcher("book", listBookDataFetcher()))
                .type(newTypeWiring("Book").dataFetcher("author", getAuthorDataFetcher()))
                .build();
        // 创建GraphQLSchema,绑定typeDefinitionRegistry和runtimeWiring
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);

        GraphQL graphQL = GraphQL.newGraphQL(graphQLSchema).build();

        /* 构建查询语句 */
        String query = Resources.toString(Resources.getResource("simple/query.graphqls"), Charsets.UTF_8);
        ExecutionResult executionResult = graphQL.execute(newExecutionInput()
                // 查询语句
                .query(query)
                // 查询变量
                .variables(ImmutableMap.of("id", "book-1", "withAuthor", true)));

        System.out.println(executionResult.toString());
    }
}
