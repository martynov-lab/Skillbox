import 'package:flutter/material.dart';
import 'package:multiplatform_solutions/model/person.dart';
import 'package:multiplatform_solutions/page/person_details.dart';
import 'package:multiplatform_solutions/page/person_grid.dart';
import 'package:multiplatform_solutions/page/person_list.dart';

class WideLayout extends StatelessWidget {
  final int currentPerson;
  final void Function(int) onPersonTap;
  const WideLayout({
    Key? key,
    required this.currentPerson,
    required this.onPersonTap,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        Expanded(
          flex: 1,
          child: Container(
            color: Colors.blue[700],
          ),
        ),
        Expanded(
          child: PersonGrid(
              currentPerson: currentPerson, onPersonTap: onPersonTap),
          flex: 3,
        ),
      ],
    );
  }
}
