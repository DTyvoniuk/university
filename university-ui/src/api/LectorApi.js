import axios from 'axios';

const LectorApi = {
  create(lector) {
    return axios.post(`/api/v1/lector/create`, lector);
  },
  searchByQuery(query) {
    return axios.get(`/api/v1/lector/search?query=${query}`);
  },
  getAll() {
    return axios.get('/api/v1/lector/all');
  },
  promote(lectorId) {
    return axios.put(`/api/v1/lector/${lectorId}/promote`);
  }
};

export default LectorApi;
