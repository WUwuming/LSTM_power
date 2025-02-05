import {createRouter, createWebHistory} from "vue-router"


let routes = [{
    name: 'Home',
    path: '/home',
    component: () => import('../views/Home.vue'),
}, {
    path: "",
    redirect: "/home",
}]

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