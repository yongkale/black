import Vue from 'vue'
import Router from 'vue-router'
import Home from './../pages/home.vue'
import Detail from './../pages/detail.vue'

Vue.use(Router)
let routes = [{
        path: '*',
        redirect: '/home'
    },
    {
        path: '/home',
        name: 'Home',
        component: Home
    },
    {
        path: '/detail',
        name: 'Detail',
        component: Detail
    }

]

routes = [...routes];

export default new Router({
    routes
})