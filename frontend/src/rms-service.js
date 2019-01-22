import Api from './api.js'

const api = Api();

export default {
  isDev() {
    return process.env.NODE_ENV && process.env.NODE_ENV === 'development'
  },
  getHouseholds() {
    return api.get('/households')
      .then(response => response.data)
      .catch(err => {
        console.log(err)
      });
  },
  getUsers() {
    return api.get('/users')
      .then(response => response.data)
      .catch(err => {
        console.log(err)
      });
  },
  getCurrentUser() {
    return api.get('/users/current')
      .then(response => response.data)
      .catch(err => {
        console.log(err)
      });
  },
  getShoppingLists() {
    return api.get('/shoppinglists')
      .then(response => response.data)
      .catch(err => {
        console.log(err)
      });
  },
  getShoppingItems() {
    return api.get('/shoppingitems')
      .then(response => response.data)
      .catch(err => console.log(err));

  },
  getHousehold(id) {
    return api.get(`/households/${Number(id)}`)
      .then(response => response.data)
      .catch(err => console.log(err));
  },
  getUserHouseholds(uid) {
    return this.getUser(uid)
      .then(user => user.households)
      .catch(err => console.log(err));
  },
  getUser(id) {
    return api.get(`/users/${Number(id)}`)
      .then(response => response.data)
      .catch(err => console.log(err));
  },
  getShoppingList(id) {
    return api.get(`/shoppinglists/${Number(id)}`)
      .then(response => response.data)
      .catch(err => console.log(err));
  },
  getHousheoldShoppingList(id) {
    return api.get(`/shoppinglists/${Number(id)}`)
      .then(response => response.data)
      .catch(err => console.log(err));
  },
  getShoppingItem(id) {
    return api.get(`/shoppingitems/${Number(id)}`);
  },

  buyShoppingItem(id) {
    return api.post(`/shoppingitems/buy/${Number(id)}`);
  }
}
