/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { DomaineService } from 'app/entities/domaine/domaine.service';
import { IDomaine, Domaine } from 'app/shared/model/domaine.model';

describe('Service Tests', () => {
  describe('Domaine Service', () => {
    let injector: TestBed;
    let service: DomaineService;
    let httpMock: HttpTestingController;
    let elemDefault: IDomaine;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(DomaineService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Domaine(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a Domaine', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new Domaine(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Domaine', async () => {
        const returnedFromService = Object.assign(
          {
            nom: 'BBBBBB',
            service: 'BBBBBB',
            responsableService: 'BBBBBB',
            numTelResponsableService: 'BBBBBB',
            emailResponsableService: 'BBBBBB',
            division: 'BBBBBB',
            responsableDivision: 'BBBBBB',
            numResponsableDivision: 'BBBBBB',
            emailResponsableDivision: 'BBBBBB',
            departement: 'BBBBBB',
            responsableDepartement: 'BBBBBB',
            numeroTelResponsableDepartement: 'BBBBBB',
            emailResponsableDepartement: 'BBBBBB',
            direction: 'BBBBBB',
            responsableDirection: 'BBBBBB',
            numeroTelDirecteur: 'BBBBBB',
            emailDirecteur: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of Domaine', async () => {
        const returnedFromService = Object.assign(
          {
            nom: 'BBBBBB',
            service: 'BBBBBB',
            responsableService: 'BBBBBB',
            numTelResponsableService: 'BBBBBB',
            emailResponsableService: 'BBBBBB',
            division: 'BBBBBB',
            responsableDivision: 'BBBBBB',
            numResponsableDivision: 'BBBBBB',
            emailResponsableDivision: 'BBBBBB',
            departement: 'BBBBBB',
            responsableDepartement: 'BBBBBB',
            numeroTelResponsableDepartement: 'BBBBBB',
            emailResponsableDepartement: 'BBBBBB',
            direction: 'BBBBBB',
            responsableDirection: 'BBBBBB',
            numeroTelDirecteur: 'BBBBBB',
            emailDirecteur: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Domaine', async () => {
        const rxPromise = service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
