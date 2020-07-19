import stomp from 'stompjs'
import{SERVER,USERNAME,PASSWORD} from '../../rabbitmq-configs'

let mq={};

/**
 * 内部变量，保留
 */
mq.client = stomp.client(SERVER);

/**
 * @param {string} topic 主题设置
 * @param {function(frame) => void} onMessage 消息响应回调函数
 * @param {function(frame) => void} onFailed 错误响应回调函数
 * 
 */
mq.connect = function(topic,onMessage,onFailed){
    mq.client.connect({login:USERNAME,passcode:PASSWORD},function(){
        mq.client.subscribe(topic,onMessage,{ack:'client'});
    },onFailed);
}

export default mq