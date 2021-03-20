export function dataDicTransformPipe(list, key, value) {
    for (const item of list) {
        if (item[key] === value) {
            return item;
        }
    }
    return null;
}