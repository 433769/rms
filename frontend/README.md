# rms-vue

> A Vue.js project

## Build Setup

``` bash
# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report
```

For detailed explanation on how things work, checkout the [guide](http://vuejs-templates.github.io/webpack/) and [docs for vue-loader](http://vuejs.github.io/vue-loader).

# to load and run all from rms-web
```
cd ../frontend && npm run build && rm -rf ../rms-web/src/main/resources/WEB-INF/* && cp -r ./dist/* ../rms-web/src/main/resources/WEB-INF/ && cd ../rms-web && mvn install cargo:run 
```
