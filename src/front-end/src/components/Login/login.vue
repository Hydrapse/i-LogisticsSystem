<template>
    <div class="body">
        <div class="box">
            <h2>Login</h2>
            <form>
                <div class="inputBox">
                    <input type="text" name="" required="" />
                    <label>Username</label>
                </div>
                <div class="inputBox">
                    <input type="password" name="" required="" />
                    <label>Password</label>
                </div>
                <div class="inputBox">
                    <input type="text" class="verify" name="" required="" />
                    <label>VerifyCode</label>
                    <verify-code style="position:absolute;left: 300px;top:10px;"></verify-code>
                </div>

                <router-link to="/main"><input type="submit" name="" value="submit" /></router-link>


            </form>
        </div>
    </div>
</template>

<script>
    import verifyCode from './verify-code'
    import VerifyCode from "./verify-code";
    export default {
        name: "login",
        components: {VerifyCode},
        component:{
            verifyCode,
        }
    }
</script>

<style type="text/css" src="../../assets/css/login.css" scoped>

</style>