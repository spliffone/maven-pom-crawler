# Maven POM Crawler

Library to retrieve Maven POM information including there referenced dependencies.

## Usage

```java
CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
httpclient.start();
Crawler crawler = new Crawler(new MavenDownloaderImpl(httpclient));
List<Pom> result = crawler.crawl(MavenId
    .builder()
    .groupId("org.springframework.boot")
    .artifactId("spring-boot")
    .version("2.6.6")
    .build());
```
