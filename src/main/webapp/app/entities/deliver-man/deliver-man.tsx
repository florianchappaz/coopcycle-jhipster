import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { openFile, byteSize, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './deliver-man.reducer';
import { IDeliverMan } from 'app/shared/model/deliver-man.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const DeliverMan = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const deliverManList = useAppSelector(state => state.deliverMan.entities);
  const loading = useAppSelector(state => state.deliverMan.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="deliver-man-heading" data-cy="DeliverManHeading">
        <Translate contentKey="coopcycleApp.deliverMan.home.title">Deliver Men</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="coopcycleApp.deliverMan.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="coopcycleApp.deliverMan.home.createLabel">Create new Deliver Man</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {deliverManList && deliverManList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="coopcycleApp.deliverMan.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="coopcycleApp.deliverMan.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="coopcycleApp.deliverMan.age">Age</Translate>
                </th>
                <th>
                  <Translate contentKey="coopcycleApp.deliverMan.profilePicture">Profile Picture</Translate>
                </th>
                <th>
                  <Translate contentKey="coopcycleApp.deliverMan.cooperative">Cooperative</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {deliverManList.map((deliverMan, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${deliverMan.id}`} color="link" size="sm">
                      {deliverMan.id}
                    </Button>
                  </td>
                  <td>{deliverMan.name}</td>
                  <td>{deliverMan.age}</td>
                  <td>
                    {deliverMan.profilePicture ? (
                      <div>
                        {deliverMan.profilePictureContentType ? (
                          <a onClick={openFile(deliverMan.profilePictureContentType, deliverMan.profilePicture)}>
                            <img
                              src={`data:${deliverMan.profilePictureContentType};base64,${deliverMan.profilePicture}`}
                              style={{ maxHeight: '30px' }}
                            />
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {deliverMan.profilePictureContentType}, {byteSize(deliverMan.profilePicture)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>
                    {deliverMan.cooperative ? <Link to={`cooperative/${deliverMan.cooperative.id}`}>{deliverMan.cooperative.id}</Link> : ''}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${deliverMan.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${deliverMan.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${deliverMan.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="coopcycleApp.deliverMan.home.notFound">No Deliver Men found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default DeliverMan;
