package ${package.Service};

import com.baomidou.mybatisplus.plugins.Page;
import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import com.dcy.model.BootStrapTable;

import java.util.List;

/**
 * <p>
    * $!{table.comment} 服务类
    * </p>
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**
     *  分页查询
     * @param bootStrapTable
     * @param ${table.entityPath}
     * @return
     */
    Page<${entity}> selectPage(BootStrapTable<${entity}> bootStrapTable,${entity} ${table.entityPath});


    List<AppCertificate> selectList(${entity} ${table.entityPath});
    }



    serviceImpl.java.vm

    package ${package.ServiceImpl};

    import com.baomidou.mybatisplus.mapper.EntityWrapper;
    import com.baomidou.mybatisplus.plugins.Page;
    import com.dcy.model.BootStrapTable;
    import ${package.Entity}.${entity};
    import ${package.Mapper}.${table.mapperName};
    import ${package.Service}.${table.serviceName};
    import ${superServiceImplClassPackage};
    import org.springframework.stereotype.Service;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.transaction.annotation.Transactional;
    import com.dcy.utils.lang.StringUtils;

    import java.util.List;
    /**
    * <p>
        * $!{table.comment} 服务实现类
        * </p>
    *
    * @author ${author}
    * @since ${date}
    */
    @Service
    @Transactional
    public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {


    @Autowired
    private ${table.mapperName} ${table.entityPath}Mapper;

    @Override
    public Page<${entity}> selectPage(BootStrapTable<${entity}> bootStrapTable, ${entity} ${table.entityPath}) {
    EntityWrapper<${entity}> entityWrapper = new EntityWrapper<${entity}>();
    getEntityWrapper(entityWrapper,${table.entityPath});
    return super.selectPage(bootStrapTable.getPagePlus(),entityWrapper);
    }

    @Override
    public List<${entity}> selectList(${entity} ${table.entityPath}) {
    EntityWrapper<${entity}> entityWrapper = new EntityWrapper<${entity}>();
    getEntityWrapper(entityWrapper,${table.entityPath});
    return super.selectList(entityWrapper);
    }

    /**
    *  公共查询条件
    * @param entityWrapper
    * @return
    */
    public EntityWrapper<${entity}> getEntityWrapper(EntityWrapper<${entity}> entityWrapper,${entity} ${table.entityPath}){
    //条件拼接
    #foreach($field in ${table.fields})
    #if(!${field.keyFlag})
    if (StringUtils.isNotBlank(${table.entityPath}.${getprefix}${field.capitalName}())){
    entityWrapper.like(${entity}.${field.name.toUpperCase()},${table.entityPath}.${getprefix}${field.capitalName}());
    }
    #end
    #end
    return entityWrapper;
    }
    }