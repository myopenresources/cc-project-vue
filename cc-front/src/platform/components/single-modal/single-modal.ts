import { Modal } from 'ant-design-vue';

export default class SingleModal {
    private static modal: any = undefined;

    static open(title: string, content: string, cb: Function) {
        if (SingleModal.modal === undefined) {
            SingleModal.modal = Modal.warning({
                title: title,
                content: content,
                onOk() {
                    cb();
                }
            });
        }
        return SingleModal.modal;
    }

    static close() {
        if (SingleModal.modal !== undefined) {
            SingleModal.modal.destroy();
            SingleModal.modal = undefined;
        }
    }
}