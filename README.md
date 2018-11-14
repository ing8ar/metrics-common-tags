# Metrics Common Tags
Micro-util for adding common tags to sending metrics (spring boot actuator).
## Getting Started
#### 1. Add dependency to build file (build.gradle.kts)
```
implementation("io.github.ing8ar.metrics-common-tags:metrics-common-tags-autoconfigure:0.1.0")
```
#### 2. Add properties to your spring boot application.yml
```yaml
metrics:
  common:
    tags:
      - app
      - host
```
#### 3. Run app
Your app will add that tags with values for each metric:

Key|Value
---|-----
app|${spring.application.name}
host|InetAddress.getLocalHost().hostName
## Pre-initialize tags
Tag|Extractor class
---|---------------
app|DefaultAppNameTagExtractor
host|DefaultHostNameTagExtractor
## Customization
You can add your own tags, or override exist.
#### Add your own tag
Create bean which implements TagExtractor:
```kotlin
    @Bean
    open fun customTagExtractor() = object : TagExtractor {
        override val tagName: String
            get() = "custom-tag"

        override fun extract() = "custom tag data"

    }
```
, then add tag name to configuration file (ex.: application.yml):
```yaml
metrics:
  common:
    tags:
      - custom-tag
```
#### Override exist
See example project 
