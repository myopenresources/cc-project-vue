// const postcssCustomProperties = require('postcss-custom-properties');
//const postcssColorMod = require('postcss-color-mod-function');
const autoprefixer = require('autoprefixer');


module.exports = {
    plugins: [
        /*postcssCustomProperties({
            preserve: false
        }),*/
        //postcssColorMod()
        autoprefixer({
            grid:true
        }),
    ]
}