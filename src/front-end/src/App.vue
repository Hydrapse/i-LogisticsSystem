<template>
  <div id="app" >
      <router-view v-if="isRouterAlive"/>

      <!-- 需要缓存的视图组件 -->
<!--      <keep-alive :include="OrderExamine">-->
<!--          <router-view v-if="$route.meta.keepAlive"></router-view>-->
<!--      </keep-alive>-->

      <!-- 不需要缓存的视图组件 -->
<!--      <router-view v-if="!$route.meta.keepAlive"></router-view>-->

  </div>
</template>

<script>


export default {
  name: 'app',
  provide() {
    return {
      reload: this.reload
    }
  },
  data() {
    return {
      isRouterAlive: true,
      include: [],
    }
  },
  methods: {
    reload() {
      this.isRouterAlive = false
      this.$nextTick(function () {
        this.isRouterAlive = true
      })
    }
  },
  // watch: {
  //   $route(to, from) {
  //     //如果 要 to(进入) 的页面是需要 keepAlive 缓存的，把 name push 进 include数组
  //     if (to.meta.keepAlive) {
  //       console.log(111)
  //       !this.include.includes(to.name) && this.include.push(to.name);
  //     }
  //     //如果 要 from(离开) 的页面是 keepAlive缓存的，
  //     //再根据 deepth 来判断是前进还是后退
  //     //如果是后退
  //     if (from.meta.keepAlive /*&& to.meta.deepth < from.meta.deepth*/) {
  //       console.log(222)
  //       var index = this.include.indexOf(from.name);
  //       index !== -1 && this.include.splice(index, 1);
  //     }
  //   }
  // },
}
</script>

<style>
#app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    height:100%;
  /*margin-top: 60px;*/
}
html,body{
    height:100%;
    margin: 0;
}
</style>
