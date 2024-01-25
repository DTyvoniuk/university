import axios from 'axios';

const DepartmentApi = {
  create(department) {
    return axios.post(`/api/v1/department/create`, department);
  },
  getAllDepartmentsWithStatistic() {
    return axios.get('/api/v1/department/all/statistic');
  }
};

export default DepartmentApi;
