// localstorage封装
var MyStorage = (function mystorage () {
  var ms = 'mystorage'
  var storage = window.localStorage

  var test = function () {
    if (!window.localStorage) {
      return false
    } else {
      return true
    }
  }

  var setItem = function (key, value) {
    // 存储
    var mydata = storage.getItem(ms)
    if (!mydata) {
      this.init()
      mydata = storage.getItem(ms)
    }
    mydata = JSON.parse(mydata)
    mydata.data[key] = value
    storage.setItem(ms, JSON.stringify(mydata))
    return mydata.data
  }

  var getItem = function (key) {
    // 读取
    var mydata = storage.getItem(ms)
    if (!mydata) {
      return false
    }
    mydata = JSON.parse(mydata)

    return mydata.data[key]
  }

  var removeItem = function (key) {
    // 读取
    var mydata = storage.getItem(ms)
    if (!mydata) {
      return false
    }

    mydata = JSON.parse(mydata)
    delete mydata.data[key]
    storage.setItem(ms, JSON.stringify(mydata))
    return mydata.data
  }

  var clear = function () {
    // 清除对象
    storage.removeItem(ms)
  }

  var init = function () {
    storage.setItem(ms, '{"data":{}}')
  }

  return {
    test: test,
    setItem: setItem,
    getItem: getItem,
    removeItem: removeItem,
    init: init,
    clear: clear
  }
})()

export default MyStorage
