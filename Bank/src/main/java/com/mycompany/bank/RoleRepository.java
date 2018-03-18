/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank;

/**
 *
 * @author Nick-PC
 */

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository("roleRepository")
public interface RoleRepository extends CrudRepository<Role, Integer>{
	Role findByRole(String role);

}
