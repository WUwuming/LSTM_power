import {createRouter, createWebHistory} from "vue-router"


let routes = [{
    name: 'Gate',
    path: '/gate',
    component: () => import('../views/Gate.vue'),
    children: [
        {
            name: 'Home',
            path: '/gate/home',
            component: () => import('../views/GateChild/Home.vue')
        }, {
            name: 'Introduce',
            path: '/gate/introduce',
            component: () => import('../views/GateChild/Introduce.vue')
        }, {
            name: 'Pay',
            path: '/gate/pay',
            component: () => import('../views/GateChild/Pay.vue')
        }, {
            name: 'About',
            path: '/gate/about',
            component: () => import('../views/GateChild/About.vue')
        }, {
            path: "/gate",
            redirect: '/gate/home'
        }
    ]
}, {
    name:'Login',
    path: '/login',
    component:()=>import('../views/Login.vue')
}, {
    name:'Register',
    path: '/register',
    component:()=>import('../views/Register.vue')
}, {
    path: "",
    redirect: "/gate",
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