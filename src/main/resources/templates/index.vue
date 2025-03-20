<template>
    <div id="app">
        <h1>登录表单</h1>
        <form @submit.prevent="onSubmit">
            <div>
                <label for="username">账号:</label>
                <input type="text" id="username" v-model="username" required />
            </div>
            <div>
                <label for="password">密码:</label>
                <input type="password" id="password" v-model="password" required />
            </div>
            <button type="submit">登录</button>
        </form>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                username: '',
                password: ''
            };
        },
        methods: {
            onSubmit() {
                alert(`账号: ${this.username}, 密码: ${this.password}`);
            }
        }
    };
</script>

<style>
    #app {
        font-family: Avenir, Helvetica, Arial, sans-serif;
        text-align: center;
        color: #2c3e50;
        margin-top: 60px;
    }

    form {
        display: inline-block;
        text-align: left;
        margin: 0 auto;
    }

    label {
        display: block;
        margin-top: 10px;
    }

    input {
        width: 100%;
        padding: 8px;
        margin-top: 5px;
        box-sizing: border-box;
    }

    button {
        margin-top: 10px;
        padding: 10px 20px;
        background-color: #4CAF50;
        color: white;
        border: none;
        cursor: pointer;
    }

    button:hover {
        background-color: #45a049;
    }
</style>