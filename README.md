# spring-jooq-with-jpa
test jooq with jpa

jOOQ DSL 코드를 생성하기 위해서는 plugin 내 jdbc url, username, password를 본인 DB 정보로 입력해야 합니다.

또한 DSL 코드의 패키지명을 원하는대로 입력해야하는데 저는 ```com.tistory.jaimemin.jooq.code```로 설정했기 때문에 CRUDTest에서 TeamRecord와 MemberRecord 패키지 경로가 아래와 같습니다.

import com.tistory.jaimemin.jooq.code.jooq.tables.records.MemberRecord;

import com.tistory.jaimemin.jooq.code.jooq.tables.records.TeamRecord;

```
<!-- jOOQ Code Generation Plugin -->
            <plugin>
                <groupId>org.jooq</groupId>
                <artifactId>jooq-codegen-maven</artifactId>
                <version>3.14.16</version> <!-- Use the appropriate version -->

                <executions>
                    <execution>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                    </execution>
                </executions>

                <configuration>
                    <jdbc>
                        <driver>org.mariadb.jdbc.Driver</driver>
                        <url>[JDBC URL]</url>
                        <user>[DB username]</user>
                        <password>[DB password]</password>
                    </jdbc>
                    <generator>
                        <name>org.jooq.codegen.DefaultGenerator</name>
                        <database>
                            <name>org.jooq.meta.mariadb.MariaDBDatabase</name>
                            <!-- Other database configurations if needed -->
                        </database>
                        <generate>
                        </generate>
                        <target>
                            <!-- Specify the package where the generated classes will be placed -->
                            <packageName>[원하는 DSL 패키지명]</packageName>
                            <!-- Specify the target directory for the generated classes -->
                            <directory>target/generated-sources/jooq</directory>
                        </target>
                    </generator>
                </configuration>
            </plugin>
```

또한, applicaition.yml도 jdbc url, username, password를 알맞게 입력해야 합니다.

```
spring:
  datasource:
#    url: jdbc:tc:mariadb:10.9.2://test
    url: [jdbc URL]
    username: [DB username]
    password: [DB papssword]
    driver-class-name: org.mariadb.jdbc.Driver
```

그 후, ```mvn clean install``` 혹은 ```mvn clean generate-sources``` 명령어를 통해 QClass와 jOOQ DSL 코드를 생성할 수 있습니다.
