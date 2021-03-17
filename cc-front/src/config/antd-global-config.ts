import { notification, message } from "ant-design-vue";

export function notificationConfig() {
    notification.config({
        duration: 2,
    });
}

export function messageConfig() {
    message.config({
        duration: 2,
    });
}

export function antdGloabalConfig() {
    notificationConfig();
    messageConfig();
}


