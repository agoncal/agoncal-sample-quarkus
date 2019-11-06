# Samples - Quarkus - JPA JTA

This sample shows a JPA entity (`Address` ) and a JTA transactional service (`AddressService`) being tested with and without Quarkus.
This idea is to show how many changes you have to do in your code to be able to make your JPA/JTA code work with Quarkus.
Being a full JPA-JTA application, we have a `beans.xml` and `persistence.xml` deployment descriptors

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
But thanks to [Gunnar Morling](https://twitter.com/gunnarmorling) and his blog post [Testing CDI Beans and the Persistence Layer Under Java SE](https://in.relation.to/2019/01/23/testing-cdi-beans-and-persistence-layer-under-java-se) there is a way of using a Weld as the container.
As you can see, to execute the test we need a few extra artefacts that just the `AddressServiceTest`:

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

# Licensing

<a rel="license" href="http://creativecommons.org/licenses/by-sa/3.0/"><img alt="Creative Commons License" style="border-width:0" src="http://i.creativecommons.org/l/by-sa/3.0/88x31.png" /></a><br />This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-sa/3.0/">Creative Commons Attribution-ShareAlike 3.0 Unported License</a>.

<div class="footer">
    <span class="footerTitle"><span class="uc">a</span>ntonio <span class="uc">g</span>oncalves</span>
</div>
