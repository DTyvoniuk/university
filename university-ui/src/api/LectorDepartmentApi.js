import axios from 'axios';

const LectorDepartmentApi = {
  assign(lectorId, departmentId) {
    return axios.post(`api/v1/lector/${lectorId}/department/${departmentId}/assign`);
  },
  unassign(lectorId, departmentId) {
    return axios.delete(`api/v1/lector/${lectorId}/department/${departmentId}/unassign`);
  },
};

export default LectorDepartmentApi;