# web-crawler

Requirements:

Please write a simple web crawler in a language of your choice.  (Please be aware that we favor Apple hardware, so Microsoft based solutions require the use of containers or virtual machines).

The crawler should be limited to one domain. Given a starting URL – say http://wiprodigital.com - it should visit all pages within the domain, but not follow the links to external sites such as Google or Twitter.

The output should be a simple structured site map (this does not need to be a traditional XML sitemap - just some sort of output to reflect what your crawler has discovered) showing links to other pages under the same domain, links to external URLs, and links to static content such as images for each respective page.

---

### Design decisions

Use 4 threads using ExecutorService (pool)

---

`mvn spring-boot:run -Dspring-boot.run.arguments={url}`


### Approach

---

A radix tree is well-suited to represent the data structure:
https://bitbucket.org/drosario69/daily-coding-problems/src/master/medium-problem_011/
Rather than each node being a letter, each node will be a word. It makes traversal simpler and logical.
