# Samples - Quarkus - JPA JTA

Let's say you have an application using JPA and JTA apis and want to migrate it to Quarkus.
Not only you won't be changing much your legacy code so it can run with Quarkus, but you will simplify your tests.

## Use Case

This sample shows a JPA entity (`Address`) and a JTA transactional service (`AddressService`) annotated with the CDI qualifier `@Transactional` being tested with and without Quarkus.
Being a full JPA-JTA application, we also need a `beans.xml` and `persistence.xml` deployment descriptors

``` 
└── src
    └── main
        ├── java
        │   └── org
        │       └── agoncal
        │           └── sample
        │               └── quarkus
        │                   └── without
        │                       ├── Address.java
        │                       └── AddressService.java
        └── resources
            └── META-INF
                ├── beans.xml
                └── persistence.xml
```

## Without Quarkus

To test a JPA + JTA application you usually need [Arquillian](http://arquillian.org/) and an application server (eg. Wildfly).
But thanks to [Gunnar Morling](https://twitter.com/gunnarmorling) and his blog post [Testing CDI Beans and the Persistence Layer Under Java SE](https://in.relation.to/2019/01/23/testing-cdi-beans-and-persistence-layer-under-java-se) there is a way of using a Weld (CDI implementation) as the container.
As you can see, to execute the test we need a few extra artifacts that just the `AddressServiceTest`:

``` 
└── src
    └── test
        ├── java
        │   └── org
        │       └── agoncal
        │           └── sample
        │               └── quarkus
        │                   └── without
        │                       ├── AddressServiceTest.java
        │                       ├── EntityManagerFactoryProducer.java
        │                       ├── EntityManagerProducer.java
        │                       ├── JtaEnvironment.java
        │                       ├── TestingTransactionServices.java
        │                       └── TransactionalConnectionProvider.java
        └── resources
            ├── META-INF
            │   ├── beans.xml
            │   └── services
            │       └── org.jboss.weld.bootstrap.api.Service
            ├── jndi.properties
            └── log4j.properties
```

## With Quarkus

The first good thing with Quarkus is that we don't need to change any of our business code (not even to add a `application.properties`).
Why? Because it has JPA, CDI and JTA extensions.
Then, the good news is that you don't need all the extra artifacts brought previsously by the CDI container.
We just need a simple `@QuarkusTest` annotation on the `AddressServiceTest` test class.

``` 
└── src
    └── test
        └── java
            └── org
                └── agoncal
                    └── sample
                        └── quarkus
                            └── without
                                └── AddressServiceTest.java
```

The only problem I found was the Hibernate dialect that wasn't set in the `persistence.xml`. 
So I had this exception:

``` 
HibernateException: Access to DialectResolutionInfo cannot be null when 'hibernate.dialect' not set
```

It was just a matter of setting the dialect in the `persistence.xml`:

``` 
<property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect"/>
```

# Licensing

<a rel="license" href="http://creativecommons.org/licenses/by-sa/3.0/"><img alt="Creative Commons License" style="border-width:0" src="http://i.creativecommons.org/l/by-sa/3.0/88x31.png" /></a><br />This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-sa/3.0/">Creative Commons Attribution-ShareAlike 3.0 Unported License</a>.

<div class="footer">
    <span class="footerTitle"><span class="uc">a</span>ntonio <span class="uc">g</span>oncalves</span>
</div>
