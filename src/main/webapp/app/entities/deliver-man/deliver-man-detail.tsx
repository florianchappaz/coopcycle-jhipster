import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './deliver-man.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const DeliverManDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const deliverManEntity = useAppSelector(state => state.deliverMan.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="deliverManDetailsHeading">
          <Translate contentKey="coopcycleApp.deliverMan.detail.title">DeliverMan</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{deliverManEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="coopcycleApp.deliverMan.name">Name</Translate>
            </span>
          </dt>
          <dd>{deliverManEntity.name}</dd>
          <dt>
            <span id="age">
              <Translate contentKey="coopcycleApp.deliverMan.age">Age</Translate>
            </span>
          </dt>
          <dd>{deliverManEntity.age}</dd>
          <dt>
            <span id="profilePicture">
              <Translate contentKey="coopcycleApp.deliverMan.profilePicture">Profile Picture</Translate>
            </span>
          </dt>
          <dd>
            {deliverManEntity.profilePicture ? (
              <div>
                {deliverManEntity.profilePictureContentType ? (
                  <a onClick={openFile(deliverManEntity.profilePictureContentType, deliverManEntity.profilePicture)}>
                    <img
                      src={`data:${deliverManEntity.profilePictureContentType};base64,${deliverManEntity.profilePicture}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                ) : null}
                <span>
                  {deliverManEntity.profilePictureContentType}, {byteSize(deliverManEntity.profilePicture)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="coopcycleApp.deliverMan.cooperative">Cooperative</Translate>
          </dt>
          <dd>{deliverManEntity.cooperative ? deliverManEntity.cooperative.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/deliver-man" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/deliver-man/${deliverManEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DeliverManDetail;
