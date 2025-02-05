import {createRouter, createWebHistory} from "vue-router"

let routes = new VueRouter({
    routes: [{
        name: 'home',
        path: '/',
        component: Home,
    }]
})

const router = createRouter({
    history: createWebHistory(),
    routes,
})

router.beforeEach((to, from, next) => {
        let isAuthenticated = localStorage.getItem('token')
        if (to.name !== 'login' && !isAuthenticated && to.meta.requiredAuth) next({name: 'Login'})
        else next()
    }
)

export default router