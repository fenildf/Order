package com.omdasoft.orderonline.service.org;

import java.util.List;

import com.omdasoft.orderonline.domain.org.Department;
import com.omdasoft.orderonline.domain.user.SysUser;
import com.omdasoft.orderonline.model.common.PageStore;
import com.omdasoft.orderonline.model.org.DepartmentVo;
import com.omdasoft.orderonline.model.org.exception.DepartmentDeleteException;
import com.omdasoft.orderonline.model.org.search.DepartmentListVo;
import com.omdasoft.orderonline.model.org.search.DepartmentManageVo;
import com.omdasoft.orderonline.model.user.UserContext;

/**
 * The logic about {@link Department}
 * 
 * @author yanxin
 * @since 1.0
 */
public interface DepartmentLogic {

	/**
	 * Find department by id.
	 * 
	 * @return
	 */
	public Department findDepartmentById(String id);

	/**
	 * Add a new department.
	 * 
	 * @param caller
	 * @param department
	 * @return
	 */
	public Department saveDepartment(SysUser caller, Department department);

	/**
	 * Only supported edit the name and description.
	 * 
	 * @param caller
	 * @param id
	 * @param department
	 * @return
	 */
	public Department editDepartment(SysUser caller, String id,
			Department department);

	/**
	 * Delete the specified Department. Notice: you are only permitted to delete
	 * the leaf Department node.
	 * 
	 * @param deptId
	 * @return 
	 * @throws DepartmentDeleteException
	 */
	public String deleteDepartment(String deptId) throws DepartmentDeleteException;

	/**
	 * Check whether is a leaf Department.If use index maintain, only check rgt
	 * - lft = 1. If 1 means it is a leaf node, return true. Otherwise return
	 * false.
	 * 
	 * @param department
	 * @return
	 */
	public boolean isLeaf(Department department);

	/**
	 * Get the immediacy children departments.It means if A has son B and C and
	 * B has son D, you just get the list containing A and B. If you want to get
	 * the whole children containing D. You should use
	 * {@link #getWholeChildren(String)}.
	 * 
	 * @param deptId
	 * @return
	 */
	public List<Department> getImmediacyChildren(String deptId);

	/**
	 * Get the whole children departments. It is not only contain the immediacy
	 * departments and the every sub nodes.
	 * 
	 * @param deptId
	 * @param containItSelf
	 *            true - contain itself <br>
	 *            false - contain no itself
	 * @return
	 */
	public List<Department> getWholeChildren(String deptId,
			boolean containItSelf);

	/**
	 * Get the whole children id.
	 * 
	 * More detail see {@link #getWholeChildren(String, boolean)}
	 * 
	 * @param deptId
	 * @param containItSelf
	 * @return
	 */
	public List<String> getWholeChildrenIds(String deptId, boolean containItSelf);

	/**
	 * Get immediacy departments of the specified corporation. e.g. a
	 * corporation contains IT department and Sales department and IT department
	 * divides to Development team and test team. According to this method, you
	 * just get a list containing IT and Sales. If you want to get the whole
	 * departments, you should use
	 * {@link #getWholeDepartmentsOfCorporation(String)}
	 * 
	 * @param corporationId
	 * @return
	 */
	public List<Department> getImmediacyDepartmentsOfCorporation(
			String corporationId);

	/**
	 * Get all of the departments of the specified corporation.
	 * 
	 * @param corporationId
	 * @return
	 */
	public List<Department> getWholeDepartmentsOfCorporation(
			String corporationId);

	/**
	 * Get the root department of the specified corporation.If not exist, it may
	 * create a new.
	 * 
	 * @param corpId
	 * @return
	 */
	public Department getRootDepartmentOfCorporation(String corpId);

	/**
	 * @param caller
	 * @param department
	 * @return
	 */
	public Department save(SysUser caller, Department department);

	/**
	 * @param caller
	 * @param departmentVo
	 * @return
	 */
	public PageStore<Department> departmentList(UserContext context,
			DepartmentListVo departmentVo);

	/**
	 * @param corpId
	 * @return
	 */
	public List<DepartmentManageVo> getDepartmentManageList(String corpId);

	public List<DepartmentManageVo> getDepartmentLeaderList(String leaderId,String corporcationId);
	/**
	 * @param caller
	 * @param department
	 * @return
	 */
	public Department addDepartment(SysUser caller, DepartmentVo department);

	/**
	 * @param uc
	 * @param departmentIds
	 * @return
	 */
	public String mergeDepartment(UserContext uc, String departmentIds,String departmentName,String leaderId);


	/**
	 * @param deptId
	 * @param containItSelf
	 * @return
	 */
	public List<String> getWholeChildrenNames(String deptId, boolean containItSelf);
	
	/**
	 *获取部门.查询.key(name).去掉根部门
	 * 
	 * @param corporationId
	 * @return
	 */
	public List<Department> getDepartmentsOfCorporationAndKey(String corporationId,String key);
	public List<String> getDepartmentCityName(String corporationId);
	public List<String[]> getDepartmentByCityName(String corporationId,String cityName);
	/**
	 * 根据DID查找分店Id
	 * @param id
	 * @return
	 */
	public String findDepartmentBydId(String did);
	
	/**
	 * 根据部门管理员工Id查找部门
	 * @param staffId
	 * @return
	 */
	public Department findDepartmentByAdminUserId(String userId);

}
