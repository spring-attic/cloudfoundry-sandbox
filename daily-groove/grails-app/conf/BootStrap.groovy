import static daily.groove.Constants.*

class BootStrap {
    private static final SAMPLE_FEEDS = [
            "https://feeds.bbci.co.uk/news/rss.xml": "BBC News",
            "https://www.theregister.co.uk/headlines.rss": "The Register",
            "https://www.theonion.com/feeds/daily/": "The Onion",
            "https://grails.org/plugin/latest?format=rss": "Grails Plugins",
            "https://feeds.feedburner.com/groovyblogs": "Groovy Blogs",
            "http://rss.slashdot.org/Slashdot/slashdot": "Slashdot",
            "https://spring.io/blog/": "SpringSource Team Blog",
            "https://news.sky.com/sky-news/rss/home/rss.xml": "Sky News",
            "http://feeds.dzone.com/java": "JavaLobby",
            "https://feeds.pheedo.com/techtarget/tsscom/home": "The Server Side"]

    def redis
    
    def init = { servletContext ->
        // Clear the database and start afresh...
        redis.flushall()
        
        if (!redis.scard(SAMPLE_FEEDS_KEY)) {
            SAMPLE_FEEDS.each { url, name ->
                redis.set url, name
                redis.sadd SAMPLE_FEEDS_KEY, url
            }
        }
    }

    def destroy = {
    }
}
