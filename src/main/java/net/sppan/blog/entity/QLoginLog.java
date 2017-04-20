package net.sppan.blog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLoginLog is a Querydsl query type for LoginLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLoginLog extends EntityPathBase<LoginLog> {

    private static final long serialVersionUID = -1929811559L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLoginLog loginLog = new QLoginLog("loginLog");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath ip = createString("ip");

    public final DateTimePath<java.util.Date> loginAt = createDateTime("loginAt", java.util.Date.class);

    public final QUser user;

    public QLoginLog(String variable) {
        this(LoginLog.class, forVariable(variable), INITS);
    }

    public QLoginLog(Path<? extends LoginLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLoginLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLoginLog(PathMetadata metadata, PathInits inits) {
        this(LoginLog.class, metadata, inits);
    }

    public QLoginLog(Class<? extends LoginLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

