<template>
  <div id="app">
    <TestComponents msg="Welcome to Your Vue.js App"/>
</div>
</template>

<script>
import TestComponents from './components/TestComponents.vue'

export default {
  name: 'app',
  components: {
    TestComponents
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
