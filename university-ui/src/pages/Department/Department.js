import {useEffect, useState} from "react";
import DepartmentApi from "../../api/DepartmentApi";
import DepartmentTable from "./DepartmentTable";
import NewDepartmentModal from "./NewDepartmentModal";

function Department() {
  const [departments, setDepartments] = useState([]);
  const [isModalOpen, setModalOpen] = useState(false);

  const openModal = () => {
    setModalOpen(true);
  };

  const closeModal = () => {
    setModalOpen(false);
  };
  useEffect(() => {
    getDepartments();
  }, [])

  return(
      <>
        <div className='input-container'>
          <button onClick={openModal}>Create Department</button>
          <NewDepartmentModal isOpen={isModalOpen} closeModal={closeModal} fetchTableData={getDepartments} />
        </div>
        {departments && <DepartmentTable departments={departments} reloadDepartmentPage={getDepartments} />}
      </>
  );

  function getDepartments() {
    DepartmentApi.getAllDepartmentsWithStatistic().then(({ data }) => {
      setDepartments(data);
    })
  }
}

export default Department;
