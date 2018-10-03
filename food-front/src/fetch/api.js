import axios from 'axios'

// api 路径
const HOST = 'http://localhost:8080'

export function fetch(url, method = 'GET', data) {
    return new Promise((resolve, reject) => {
        // axios.get(HOST + url)
        //     .then(response => {
        //       resolve(response.data);
        //     })
        //     .catch((error) => {
        //        reject(error)
        //     })
        var user = {
            name: 12345
        }
        console.log ('api2')
        console.log (data)
        axios({
                headers: {'Content-Type': 'application/json;charset=UTF-8'},
                method: method,
                dataType: 'json',
                url: HOST + url,
                data: data
            })
            .then((response) => {
                resolve(response)
            })
            .catch((error) => {
                reject(error)
            })
    })
}

export default {

    /**
     * 用户登陆
     * @param {any} accesstoken 
     * @returns
     */
    Login() {
        return fetch('/apiuser/login', 'POST')
    },

    /**
     * 发布活动
     * @param  data 
     * @returns
     */
    submitAction(data) {
        console.log ('api')
        console.log (data)
        return fetch('/apiaction/add', 'POST', data)
    },

    /**
     * 获取文章列表
     * @param {any} params
     * @returns
     */
    TopicsList(params) {
        return fetch(`/topics/${params}`)
    },

    /**
     * 获取文章详情
     * @param {any} id 参数id
     * @returns
     */
    TopicsDetail(id) {
        return fetch(`/topic/${id}`)
    },

    /**
     * 获取用户信息
     * @param {any} name 用户名
     * @returns
     */
    UserInfo(name) {
        return fetch(`/user/${name}`)
    },

    /**topics 
     * 获取已读和未读消息
     * @param {any} accesstoken 
     * @returns
     */
    Messages(accesstoken) {
        return fetch(`/messages?mdrender=true&accesstoken=${accesstoken}`)
    },


    /**
     * 新建主题
     * @param {any} form
     * @returns
     */
    Post(form) {
        return fetch(`/topics?${form}`, 'post')
    }

}