/**
 * Created by kale
 * http配置
 */

import axios from 'axios'

axios.defaults.timeout = 5000
axios.defaults.baseURL = 'https://localhost:8080'

//TODO加拦截器等.


export default axios