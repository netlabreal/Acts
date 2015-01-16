package net.lab.dao;

import net.lab.entity.Organizations;

import java.util.List;

/**
 * Created by lab on 25.11.2014.
 */
public interface OrgDao {
    public void AddOrg(Organizations o);
    public Integer DelOrg(Organizations o);
    public List<Organizations> GetOrgs();
    public Organizations FindOrg(String orgname);
}
