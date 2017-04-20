package net.sppan.blog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QOptions is a Querydsl query type for Options
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOptions extends EntityPathBase<Options> {

    private static final long serialVersionUID = 1671064672L;

    public static final QOptions options = new QOptions("options");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath optionKey = createString("optionKey");

    public final StringPath optionValue = createString("optionValue");

    public QOptions(String variable) {
        super(Options.class, forVariable(variable));
    }

    public QOptions(Path<? extends Options> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOptions(PathMetadata metadata) {
        super(Options.class, metadata);
    }

}

