import Cookies from 'js-cookie'

const TokenKey = 'zhny_token'
const NameKey = 'name'


export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}
export function setName(name) {
  return Cookies.set(NameKey, name)
}
export function getName() {
  return Cookies.get(NameKeys)
}
export function removeToken() {
  return Cookies.remove(TokenKey)
}