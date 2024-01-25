import Modal from 'react-modal';
import { useState } from 'react';
import LectorApi from '../../api/LectorApi';
import { NotificationManager } from 'react-notifications';
import '../../component/Modal.css';

function NewLectorModal({ isOpen, closeModal, fetchTableData }) {
  const [lectorFullName, setLectorFullName] = useState('');

  return (
      <Modal
          isOpen={isOpen}
          onRequestClose={closeModal}
          contentLabel="New Lector Modal"
          className="react-modal-content"
          overlayClassName="react-modal-overlay"
      >
        <button className="close-button" onClick={closeModal}>
          ×
        </button>
        <input onChange={handleInputChange} />
        <button onClick={saveLector}>Save</button>
      </Modal>
  );

  function handleInputChange(event) {
    setLectorFullName(event.target.value);
  }

  function saveLector() {
    LectorApi.create(lectorFullName)
        .then(() => {
          closeModal();
          fetchTableData();
          setLectorFullName('');
          NotificationManager.success('Lector successfully saved');
        })
        .catch((err) => {
          NotificationManager.error(err.response.data);
        });
  }
}

export default NewLectorModal;
