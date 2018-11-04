import Vue from 'vue'
import Router from 'vue-router'
import Home from './../pages/home.vue'
import Detail from './../pages/detail.vue'
import addAction from './../pages/add-action.vue'

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
        component: Detail,
        props: true 
    },
    {
        path: '/add-action',
        name: 'addAction',
        component: addAction
    }


]

routes = [...routes];

export default new Router({
    routes
})