module.exports = {
    devServer: {
        host: "localhost",
        port: "8082",
        https: false,
        proxy: {
            '/api': {
                target:　"http://localhost:8233",
                changeOrigin: true,
                pathRewrite: {
                    '^/api':'/'
                }
            }
        }
    }
}