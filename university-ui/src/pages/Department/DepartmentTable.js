import {Box, Typography} from "@mui/material";
import {DataGrid} from "@mui/x-data-grid";
import MenuItem from '@mui/material/MenuItem';
import Select from '@mui/material/Select';
import LectorDepartmentApi from "../../api/LectorDepartmentApi";
import {NotificationManager} from "react-notifications";

function DepartmentTable({ departments, reloadDepartmentPage }) {
  const columns = [
    {field: 'departmentName', headerName: 'Name', sortable: false, editable: false, width: 250},
    {field: 'assistantCount', headerName: 'Assistant count', sortable: false, editable: false, width: 250},
    {field: 'associateProfessorCount', headerName: 'Associate Professor count', sortable: false, editable: false, width: 250},
    {field: 'professorCount', headerName: 'Professor count', sortable: false, editable: false, width: 250},
    {
      field: 'firstAction',
      headerName: 'Assigned Lectors',
      sortable: false,
      editable: false,
      width: 250,
      renderCell: (params) => (
          <Select value="" >
            {params.row.assignedLectors.map(lector => {
              const lectorNameAndDegree = `${lector.fullName} - ${lector.degree}`;
              const lectorId = lector.id;
              const departmentId = params.row.id;
              return(
                  <MenuItem
                      key={`unassign${lectorId}${departmentId}`}
                      onClick={() => unassignLectorToDepartment(lectorId, departmentId)}>{lectorNameAndDegree}
                  </MenuItem>
              )
            })}
          </Select>
      ),
    },
    {
      headerName: 'Not Assigned Lectors',
      sortable: false,
      editable: false,
      width: 250,
      renderCell: (params) => (
          <Select value="" >
            {params.row.notAssignedLectors.map(lector => {
              const lectorNameAndDegree = `${lector.fullName} - ${lector.degree}`;
              const lectorId = lector.id;
              const departmentId = params.row.id;
              return(
                  <MenuItem
                      key={`assign${lectorId}${departmentId}`}
                      onClick={() => assignLectorToDepartment(lectorId, departmentId)}>{lectorNameAndDegree}
                  </MenuItem>
              )
            })}
          </Select>
      ),
    },
  ];

  return(
      <>
        <Typography sx={{ marginTop: '5px' }} variant="body1">
          Click On Assigned Lector for unassign lector. Click on Not Assigned Lector for assign lector.
        </Typography>
        <Box sx={{ height: 400, width: '100%' }}>
          <DataGrid
              getRowId={(row) => row.id}
              sortModel={[{ field: 'departmentName', sort: 'asc' }]}
              columns={columns}
              rows={departments}
              disableColumnMenu
              disableRowSelectionOnClick
              pageSizeOptions={[5, 10, 15]}
              initialState={{
                pagination: {
                  paginationModel: { page: 0, pageSize: 5 },
                },
              }}
          />
        </Box>
      </>
  );

  function assignLectorToDepartment(lectorId,departmentId) {
    LectorDepartmentApi.assign(lectorId,departmentId).then(() => {
      NotificationManager.success('Lector successfully assigned to Department');
      reloadDepartmentPage();
    })
  }

  function unassignLectorToDepartment(lectorId,departmentId) {
    LectorDepartmentApi.unassign(lectorId,departmentId).then(() => {
      NotificationManager.success('Lector successfully unassigned from Department');
      reloadDepartmentPage();
    })
  }
}

export default DepartmentTable;
