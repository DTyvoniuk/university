import Modal from 'react-modal';
import { useState } from 'react';
import { NotificationManager } from 'react-notifications';
import '../../component/Modal.css';
import DepartmentApi from "../../api/DepartmentApi";

function NewDepartmentModal({ isOpen, closeModal, fetchTableData }) {
  const [departmentName, setDepartmentName] = useState('');

  return (
      <Modal
          isOpen={isOpen}
          onRequestClose={closeModal}
          contentLabel="New Department Modal"
          className="react-modal-content"
          overlayClassName="react-modal-overlay"
      >
        <button className="close-button" onClick={closeModal}>
          ×
        </button>
        <input onChange={handleInputChange} />
        <button onClick={saveDepartment}>Save</button>
      </Modal>
  );

  function handleInputChange(event) {
    setDepartmentName(event.target.value);
  }

  function saveDepartment() {
    DepartmentApi.create(departmentName)
        .then(() => {
          closeModal();
          fetchTableData();
          setDepartmentName('');
          NotificationManager.success('Department successfully saved');
        })
        .catch((err) => {
          NotificationManager.error(err.response.data);
        });
  }
}

export default NewDepartmentModal;
