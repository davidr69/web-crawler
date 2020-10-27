# web-crawler

Requirements:

Please write a simple web crawler in a language of your choice.  (Please be aware that we favor Apple hardware, so Microsoft based solutions require the use of containers or virtual machines).

The crawler should be limited to one domain. Given a starting URL – say http://wiprodigital.com - it should visit all pages within the domain, but not follow the links to external sites such as Google or Twitter.

The output should be a simple structured site map (this does not need to be a traditional XML sitemap - just some sort of output to reflect what your crawler has discovered) showing links to other pages under the same domain, links to external URLs, and links to static content such as images for each respective page.

---

### Design decisions

Should we honor `robots.txt`?

Use 4 threads using ExecutorService (pool)

Spring profiles ... useful when running tests; will change InputStream from Socket to File
for integration tests.

---

`mvn spring-boot:run -Dspring-boot.run.arguments={url}`

Only follow <a>; don't try to find .onClick() or any other type of link

HTML parsing would be a snap with Groovy, XmlSlurper, and GPath, but let's do this
as "pure" Java.

Use DOM parser rather than SAX parser

Do we really need a StreamSvc if the DOM parse accepts an InputStream?

Will resort to manually parsing HTML string since XML DOM parser is throwing:

`Attribute name "async" associated with an element type "script" must be followed by the ' = ' character.`

Looks like jsoup is the easiest approach.