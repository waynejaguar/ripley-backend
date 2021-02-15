package com.ripley.authlogin.testing;


import org.springframework.boot.test.context.SpringBootTest;

@PostgresTest
@SpringBootTest
public abstract class RepositoryTest extends DatabaseCleaner {

}
